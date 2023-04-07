<?php

namespace App\Http\Controllers\Document;

use App\Http\Controllers\Controller;
use App\Models\Demande;
use Illuminate\Http\Request;

class DemandesController extends Controller
{
    public function getAllDemandes(){
        $demandes=Demande::where('etat',0)->with('document')->with('user')->get();
        return response()->json(['data'=>$demandes],200);
    }
    public function AccepterDocument(Request $request){
        $demandes_check=Demande::where('user_id',$request->id_user)->where('document_id',$request->docId)->first();
        Demande::where('user_id',$request->id_user)->where('document_id',$request->docId)->update(
            ['etat'=>1,
            'nombre'=>$demandes_check->nombre+=1
            ]
);
        return response()->json("data updated succesfully");
    }
    public function ReffuserDoccument(Request $request){
        $demandes_check=Demande::where('user_id',$request->id_user)->where('document_id',$request->docId)->first();
        Demande::where('user_id',$request->id_user)->where('document_id',$request->docId)->update(
            ['etat'=>0,
            'nombre'=>$demandes_check->nombre+=1
            ]
);
        return response()->json("data updated succesfully");
    }
}
