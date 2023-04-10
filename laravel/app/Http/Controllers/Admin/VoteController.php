<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\Notification;
use App\Models\User;
use App\Models\user_nominated;
use App\Models\Votes;
use App\Models\VoteSession;
use Illuminate\Http\Request;

class VoteController extends Controller
{
    public function StartVote(Request $request){
        $vote=VoteSession::create([
            'Titre_Vote'=>$request->titre,
            "DateDebut"=>$request->date_debut,
            'DateFin'=>$request->date_fin
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
        $vote=VoteSession::where("etat",1)->orWhere("etat",0)->first();
        if($vote){
            return response()->json(['data'=>$vote,'status'=>true]);
        }else{
            return response()->json(['data'=>$vote,'status'=>false]);
        }
    }

    public function AddUserNominated_Session(Request $request){
        $vote=user_nominated::create([
            'user_id'=>$request->user()->id,
            'vote_session_id'=>$request->idsession
        ]);
        return response()->json(['data'=>$vote],201);
    }

    public function CheckUserNomanated(Request $request){
        $vote=user_nominated::where("user_id",$request->user()->id)->first();
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

    public function Annuler(int $id){
        $check_session=VoteSession::find($id);
        if($check_session){
            $check_session->delete();
            return response()->json(['data'=>"delete with success"],200);
        }
        return response()->json(['data'=>"Not Found"],404);
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

}
