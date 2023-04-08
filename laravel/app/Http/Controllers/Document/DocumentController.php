<?php

namespace App\Http\Controllers\Document;

use App\Http\Controllers\Controller;
use App\Models\Document;
use App\Models\Demande;
use App\Models\Notification;
use App\Models\Role;
use App\Models\RoleUser;
use App\Models\User;
use Illuminate\Http\Request;

class DocumentController extends Controller
{
    public function AllDocuments(){
        $documents=Document::get();
        return response()->json(['data'=>$documents],200);
    }

    public function addDemande(Request $request){
      $demandes_check=Demande::where('user_id',$request->user()->id)->where('document_id',$request->document_id)->first();
      $nom=Document::where('id',$request->document_id)->first();
      $user_role=Role::where("Role_name","chefDepartment")->first();
      $id_chef=RoleUser::where('role_id',$user_role->id)->first();
      $notif=new Notification();
      $notif->id_envoi=$request->user()->id;
      $notif->id_recu=$id_chef->user_id;
      $notif->message=$request->user()->FirstName .' demande document '.$nom->Type;
      $notif->etat=0;
      $notif->save();
      if($demandes_check){
            $demandes=Demande::where('user_id',$request->user()->id)->where('document_id',$request->document_id)->update(['etat'=>0]);
            return response()->json(['data'=>$demandes],201);
       }else{
            $demande=new Demande();
            $demande->user_id=$request->user()->id;
            $demande->document_id=$request->document_id;
            $demande->etat=0;
            $demande->nombre=0;
            $demande->Langue=$request->langue;
            $demande->save();
            return response()->json(['data'=>$demande],201);
       }

    }

    public function getAlldemande(Request $request){
        $demandes=Demande::where('user_id',$request->user()->id)->get();
        return response()->json(['data'=>$demandes],200);
    }

    public function AccepterDocument(Request $request){
        $demandes_check=Demande::where('user_id',$request->user()->id)->where('document_id',$request->docId)->first();
        $demandes=Demande::where('user_id',$request->user()->id)->where('document_id',$request->docId)->update(['etat'=>1,'nombre'=>$demandes_check->nombre+=1]);
        return response()->json(['data'=>$demandes],200);
    }

    public function initailiser_demande(Request $request){
        $demandes=Demande::where('user_id',$request->user()->id)->where('document_id',$request->docId)->update(['etat'=>-1]);
        return response()->json(['data'=>$demandes],200);
    }
}
