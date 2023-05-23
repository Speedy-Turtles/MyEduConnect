<?php

namespace App\Http\Controllers\Document;
use App\Http\Controllers\Controller;
use App\Models\Classe;
use App\Models\Emploi;
use App\Models\Seance;
use App\Models\User;
use Illuminate\Http\Request;

class EmploiController extends Controller
{
    public function getEmploi(Request $request){
        $user_Classe_id=User::where('id',$request->user()->id)->get('classe_id');
        $classe=Classe::where('id',$user_Classe_id[0]['classe_id'])->get('nom');
        $jours=['lundi','mardi','mercredi','jeudi','vendredi','samedi'];
        $seances=[1,2,3,4,5,6];
        for($i = 0 ; $i<count($jours);$i++){
            for($j = 0 ; $j<count($seances);$j++){
                if(Seance::where('jour',$jours[$i])->where('numero',$seances[$j])->get()){
                    $data[ $jours[$i]] [ $seances[$j] ]= Seance::where('jour',$jours[$i])->where('numero',$seances[$j])->where('emploi_id',$user_Classe_id[0]['classe_id'])->with('user')->with('salle')->with('matiere')->get();
                }else{
                    $data[ $jours[$i]] [ null ]= Seance::where('jour',$jours[$i])->where('numero',$seances[$j])->where('emploi_id',$user_Classe_id[0]['classe_id'])->get();
                }
            }

        }
        return response()->json(["data"=>$data,"classe"=>$classe,200]);
    }
}
