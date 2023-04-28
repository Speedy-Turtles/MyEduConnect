<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Http\Requests\StoreVoteSession;
use App\Models\Notification;
use App\Models\Role;
use App\Models\RoleUser;
use App\Models\User;
use App\Models\user_nominated;
use App\Models\Votes;
use App\Models\VoteSession;
use Illuminate\Http\Request;

class VoteController extends Controller
{
    public function StartVote(StoreVoteSession $request){
        $vote=VoteSession::create([
            'Titre_Vote'=>$request->titre,
            "DateDebut"=>$request->date_debut,
            'DateFin'=>$request->date_fin,
            'etat'=>0
        ]);

        $users=User::whereHas('roles',function($query){
            $query->where("Role_name","ensignant");
        })->get();

        $admin=User::whereHas('roles',function($query){
            $query->where("Role_name","Admin");
        })->first();

        for($i=0;$i<count($users);$i++){
            $notif=new Notification();
            $notif->id_envoi=$admin->id;
            $notif->id_recu=$users[$i]->id;
            $notif->message='Vote Session Started Now "'.$vote->Titre_Vote.'"';
            $notif->save();
        }

        return response()->json(['data'=>$users],201);
    }

    public function test_Vote(){
        $vote=VoteSession::where("etat",1)->orWhere("etat",0)->withCount("votes")->get();
        if($vote){
            return response()->json(['data'=>$vote,'status'=>true]);
        }else{
            return response()->json(['data'=>$vote,'status'=>false]);
        }
    }

    public function AddUserNominated_Session(Request $request){
        $checkExiste=user_nominated::where('user_id',$request->user()->id)->where("vote_session_id",$request->idsession)->first();
        if($checkExiste){
            return response()->json(['data'=>"User already Nominated"],404);
        }else{
            $vote=user_nominated::create([
                'user_id'=>$request->user()->id,
                'vote_session_id'=>$request->idsession
            ]);
            return response()->json(['data'=>$vote],201);
        }

    }

    public function CheckUserNomanated($id,Request $request){
        $vote=user_nominated::where("user_id",$request->user()->id)->where("vote_session_id",$id)->first();
        if($vote){
            return response()->json(['status'=>true],201);
        }else{
            return response()->json(['status'=>false],201);
        }
    }

    public function ListNominated(){
        $vote=VoteSession::with("users")->get();
        return response()->json(['data'=>$vote],201);
    }

    public function checkUserVoted(int $id,Request $request){
        $check=Votes::where("user_id",$request->user()->id)->where("vote_session_id",$id)->first();
        if($check){
            return response()->json(['status'=>true],201);
        }else{
            return response()->json(['status'=>false],201);
        }
    }

    public function AddVote(Request $request){
        $vote=new Votes();
        $vote->user_id=$request->user()->id;
        $vote->vote_session_id=$request->session_id;
        $vote->user_nominated_id=$request->id_nominated;
        $vote->save();
        return response()->json(['data'=>$vote],201);
    }

    public function NombreVoted(int $id){
        $count=Votes::where("vote_session_id",$id)->count();
        return response()->json(['data'=>$count],200);
    }

    public function deleteSession(int $id){
        $check_session=VoteSession::find($id);
        if($check_session){
            $check_session->delete();
            return response()->json(['data'=>"delete with success"],200);
        }
        return response()->json(['data'=>"Not Found"],404);
    }

    public function Annuler(int $id){
        $check_session=VoteSession::find($id);
        if($check_session){
            $check_session->update([
                'etat'=>2
            ]);
            return response()->json(['data'=>"Annuler with success"],200);
        }
        return response()->json(['data'=>"Not Found"],404);
    }

    public function CloseVote(int $id){

        $check_session=VoteSession::find($id);
        if($check_session){
            $check_session->update([
                'etat'=>3
            ]);
            $session=VoteSession::where("etat",2)->orWhere("etat",3)->get();
            $votes=[];
            foreach($session as $value){
                $votes[]=Votes::where("vote_session_id",$value->id)->with("user_nominated")->get()->groupBy("user_nominated_id")
                ->map(function($item) use ($value){
                    return[
                        "session"=>$value,
                        "user_nominated"=>$item[0]->user_nominated,
                        "votes"=>$item->count()
                    ];
                })->sortByDesc("votes")->values()->all();
            }

            foreach($votes[0] as $value){

                if($value['session']->etat==3){
                    $checkExist=Role::where("Role_name","chefDepartment")->First();

                    if(!$checkExist){
                        $role=new Role();
                        $role->Role_name="chefDepartment";
                        $role->save();
                    }

                    $role_user=new RoleUser();
                    $role_user->user_id=$value['user_nominated']->id;
                    $role_user->role_id=$checkExist ? $checkExist->id : $role->id;
                    $role_user->status=1;
                    $role_user->save();

                    $admin=User::whereHas('roles',function($query){
                        $query->where("Role_name","Admin");
                    })->first();

                    $notif=new Notification();
                    $notif->id_envoi=$admin->id;
                    $notif->id_recu=$value['user_nominated']->id;
                    $notif->message='You Have Been voted to be new Chef Deperment"'.$value['session']->Titre_Vote.'';
                    $notif->save();

                }
            }
            return response()->json(['data'=>"Finished with success"],200);
        }
        return response()->json(['data'=>"Not Found"],404);
    }



    public function GetSessionTerminer(){

        $session=VoteSession::where("etat",2)->orWhere("etat",3)->get();
        $votes=[];
        foreach($session as $value){
            $votes[]=Votes::where("vote_session_id",$value->id)->with("user_nominated")->get()->groupBy("user_nominated_id")
            ->map(function($item) use ($value){
                return[
                    "session"=>$value,
                    "user_nominated"=>$item[0]->user_nominated,
                    "votes"=>$item->count()
                ];
            })->sortByDesc("votes")->values()->all()[0];
        }
        return response()->json(['data'=>$votes],200);

/* foreach($votes[0] as $value){

            if($value['session']->etat==3){
                $role=new Role();
                $role->Role_name="chefDepartment";
                $role->save();

                $role_user=new RoleUser();
                $role_user->user_id=$value['user_nominated']->id;
                $role_user->role_id=$role->id;
                $role_user->save();

                $admin=User::whereHas('roles',function($query){
                    $query->where("Role_name","Admin");
                })->first();

                $notif=new Notification();
                $notif->id_envoi=$admin->id;
                $notif->id_recu=$value['user_nominated']->id;
                $notif->message='You Have Been voted to be new Chef Deperment"'.$value['session']->Titre_Vote.'';
                $notif->save();

            }
        }
*/

    }

    public function suspende(int $id){
        $update_session=VoteSession::find($id);
        if($update_session['etat']==0){
            $update_session->update([
                'etat'=>1
            ]);
            return response()->json(['data'=>$update_session],200);
        }else if($update_session['etat']==1){
            $update_session->update([
                'etat'=>0
            ]);
            return response()->json(['data'=>$update_session],200);
        }else{
            return response()->json(['data'=>"Not Found"],404);
        }
    }

    public function getUserNomanitedByIdSession($idsession){
        $vote=VoteSession::with("users")->where("id",$idsession)->get();
        return response()->json(['data'=>$vote],200);
    }

    public function getUserNominated(Request $request){
        $user_nominated=user_nominated::where("user_id",$request->user()->id)->get();
        return response()->json(['data'=>$user_nominated],200);
    }

    public function getUserVoted(Request $request){
        $user_Votes=Votes::where("user_id",$request->user()->id)->get();
        return response()->json(['data'=>$user_Votes],200);
    }

    public function CountNbrVote(int $id){
        $nbr_votes=Votes::where("vote_session_id",$id)->count();
        return response()->json(['data'=>$nbr_votes],200);
    }

}
