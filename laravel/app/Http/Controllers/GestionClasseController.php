<?php

namespace App\Http\Controllers;

use App\Models\Classe;
use Illuminate\Http\Request;

class GestionClasseController extends Controller
{
    public function getAllClasses(){
        $classes=Classe::all();
        return response()->json(["data"=>$classes],200);
    }
    public function getClasseForSpecialte($id){
        $classes=Classe::where("specialite_id",$id)->get();
        return response()->json(["data"=>$classes],200);
    }
    public function AddClasse(Request $request){

        $classe=Classe::create([
            "nom"=>$request->nom,
            "specialite_id"=>$request->specialite_id,
            "emploi_id"=>null,
        ]);
        $classe->save();
        if($classe==null){
            return response()->json(["data"=>null],400);
        }
        return response()->json(["data"=>$classe],201);
    }
    public function DeleteClasse($id){
        $classe=Classe::find($id);
        if($classe!=null){
            $classe->delete();
            return response()->json(["data"=>"classe deleted succesfully"],200);
        }
        return response()->json(["data"=>"classe not found"],404);
    }
    public function getClasseByid($id){
        $classe=Classe::find($id);
        if($classe!=null){
            return response()->json(["data"=>$classe],200);
        }
        return response()->json(["data"=>"classe not found"],404);
    }
    public function updateClasse(Request $request,$id){
        $classe=Classe::find($id);
        if($classe){
            $classe->update([
                "nom"=>$request->nom,
            ]);
            return response()->json(["data"=>"data updated succesfully"],200);
        }
        return response()->json(["data"=>"classe not found"],404);
    }
}
