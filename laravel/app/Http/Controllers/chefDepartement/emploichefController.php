<?php

namespace App\Http\Controllers\ChefDepartement;

use App\Http\Controllers\Controller;
use App\Models\Classe;
use App\Models\Seance;
use App\Models\User;
use Illuminate\Http\Request;

class emploichefController extends Controller
{
    //
    public function getEmploi(Request $request){
        $class_id=$request->class_id;
        // $classe=Classe::where('id',$class_id)->get();
        $classe=Classe::find($class_id);
        $jours=['lundi','mardi','mercredi','jeudi','vendredi','samedi'];
        $seances=[1,2,3,4,5,6];
        for($i = 0 ; $i<count($jours);$i++){
            for($j = 0 ; $j<count($seances);$j++){
                if(Seance::where('jour',$jours[$i])->where('numero',$seances[$j])->get()){
                    $data[ $jours[$i]] [ $seances[$j] ]= Seance::where('jour',$jours[$i])->where('numero',$seances[$j])->where('emploi_id',$classe['emploi_id'])->with('user')->with('salle')->with('matiere')->get();
                }else{
                    $data[ $jours[$i]] [ null ]= Seance::where('jour',$jours[$i])->where('numero',$seances[$j])->where('emploi_id',$classe['emploi_id'])->get();
                }
            }
        }
        return response()->json(["data"=>$data,"classe"=>$classe,200]);
    }
    public function StoreSeance(Request $request){
        $old_seance=Seance::where('jour',$request->jour)->where('numero',$request->seance)->where('emploi_id',$request->emploi_id)->first();
        if($old_seance!=null){
            Seance::where('jour',$request->jour)->where('numero',$request->seance)->where('emploi_id',$request->emploi_id)->first()->update([
                "salle_id"=>$request->salle_id,
                "matiere_id"=>$request->matiere_id,
                "user_id"=>$request->salle_id
            ]);

        }else{
            $seance=Seance::create([
                "salle_id"=>$request->salle_id,
                "matiere_id"=>$request->matiere_id,
                "user_id"=>$request->salle_id,
                "numero"=>$request->seance,
                "duree"=>$request->duree,
                "temp_debut"=>'8',
                "temps_fin"=>'10',
                "emploi_id"=>$request->emploi_id,
                "jour"=>$request->jour
            ]);
            $seance->save();
        }
        return response()->json(["message"=>"seance updated successfully"],200);
    }
}
