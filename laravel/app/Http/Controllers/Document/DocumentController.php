<?php

namespace App\Http\Controllers\Document;

use App\Http\Controllers\Controller;
use App\Models\Document;
use App\Models\Demande;
use Illuminate\Http\Request;

class DocumentController extends Controller
{
    public function AllDocuments(){
        $documents=Document::get();
        return response()->json(['data'=>$documents],200);
    }

    public function addDemande(Request $request){
        $demande=new Demande();
        $demande->user_id=$request->user()->id;
        $demande->document_id=$request->document_id;
        $demande->etat=0;
        $demande->nombre=0;
        $demande->Langue=$request->langue;
        $demande->save();
        return response()->json(['data'=>$demande],201);
        /*$demande=Demande::create([

        ]);*/

    }
}
