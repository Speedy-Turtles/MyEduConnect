<?php

namespace App\Http\Controllers\Document;
use App\Http\Controllers\Controller;
use App\Models\Classe;
use App\Models\Emploi;
use Illuminate\Http\Request;

class EmploiController extends Controller
{
    public function getEmploi(Request $request){

        $classe=Classe::where('id',$request->user()->classe_id)->get();
        $jours=['lundi','mardi','mercredi','jeudi','vendredi','samedi'];
        $seances=[1,2,3,4,5,6,7];
        for($i = 0 ; $i<count($jours);$i++){
            for($j = 0 ; $j<count($seances);$j++){
                if(Emploi::where('jour',$jours[$i])->where('numero_seance',$seances[$j])->get()){
                    $data[ $jours[$i]] [ $seances[$j] ]= Emploi::where('jour',$jours[$i])->where('numero_seance',$seances[$j])->where('classe',$classe[0]['nom'])->with('user')->with('salle')->get();
                }else{
                    $data[ $jours[$i]] [ null ]= Emploi::where('jour',$jours[$i])->where('numero_seance',$seances[$j])->where('classe',$classe[0]['nom'])->get();
                }
            }

        }

        // $data['lundi']=Emploi::where('jour','lundi')->where('classe',$classe[0]['nom'])->with('user')->with('salle')->get();
        // $data['mardi']=Emploi::where('jour','mardi')->where('classe',$classe[0]['nom'])->with('user')->with('salle')->get();
        // $data['mercredi']=Emploi::where('jour','mercredi')->where('classe',$classe[0]['nom'])->with('user')->with('salle')->get();
        // $data['jeudi']=Emploi::where('jour','jeudi')->where('classe',$classe[0]['nom'])->with('user')->with('salle')->get();
        // $data['vendredi']=Emploi::where('jour','vendredi')->where('classe',$classe[0]['nom'])->with('user')->with('salle')->get();
        // $data['samedi']=Emploi::where('jour','samedi')->where('classe',$classe[0]['nom'])->with('user')->with('salle')->get();

        return response()->json(["data"=>$data,200]);
    }
}
