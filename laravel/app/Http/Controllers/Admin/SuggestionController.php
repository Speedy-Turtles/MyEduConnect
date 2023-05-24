<?php

namespace App\Http\Controllers\Admin;

use App\Events\notif;
use App\Http\Controllers\Controller;
use App\Http\Requests\StoreSuggestion;
use App\Models\Notification;
use App\Models\Suggestion;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Http;

class SuggestionController extends Controller
{
    public function __construct()
    {
        $this->authorizeResource(Suggestion::class,'suggestion');
    }
    /**
     * Display a listing of the resource.
     */
    public function index(Request $request)
    {
            if($request->search=='' && $request->per_page==''){
                $all_sugg=Suggestion::with("user")->paginate(3);
            }else if($request->search!='' && $request->per_page==''){
                $all_sugg=Suggestion::with("user")->where('type',$request->search)->paginate(3);
            }else if($request->search=='' && $request->per_page!=''){
                $all_sugg=Suggestion::with("user")->paginate($request->per_page);
            }else if($request->search!='' && $request->per_page!=''){
                $all_sugg=Suggestion::with("user")->where('type',$request->search)->paginate($request->per_page);
            }
            return response()->json(['data'=>$all_sugg],200);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreSuggestion $request)
    {
         $sugg=new Suggestion();
         $sugg->user_id=$request->user()->id;
         $sugg->type=$request->type;
         $sugg->Message=$request->message;
         $sugg->save();
         $admin=User::whereHas('roles',function($query){
            $query->where("Role_name","Admin");
        })->first();
        $notif=new Notification();
        $notif->id_envoi=$request->user()->id;
        $notif->id_recu=$admin->id;
        $notif->message=$request->user()->FirstName .' add a suggestion';
        $notif->save();
        broadcast(new notif($notif->message));
        return response()->json(['data'=>$sugg],201);
    }

    /**
     * Display the specified resource.
     */
    public function show(Suggestion $suggestion)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Suggestion $suggestion)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function delete(int $id)
    {
       $sugg=Suggestion::find($id);
       if($sugg){
            $sugg->delete();
            return response()->json(['data'=>"delete with success"],200);
       }else{
        return response()->json(['data'=>"Not Found"],404);
       }
    }

    public function delete_all_sugg(){
        Suggestion::query()->delete();
        return response()->json(['data' => 'Delete All'], 200);
    }
}
