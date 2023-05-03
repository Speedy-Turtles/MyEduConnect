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
        $data['lundi']=Emploi::where('jour','lundi')->where('classe',$classe[0]['nom'])->get();
        $data['mardi']=Emploi::where('jour','mardi')->where('classe',$classe[0]['nom'])->get();
        $data['mercredi']=Emploi::where('jour','mercredi')->where('classe',$classe[0]['nom'])->get();
        $data['jeudi']=Emploi::where('jour','jeudi')->where('classe',$classe[0]['nom'])->get();
        $data['vendredi']=Emploi::where('jour','vendredi')->where('classe',$classe[0]['nom'])->get();
        $data['samedi']=Emploi::where('jour','samedi')->where('classe',$classe[0]['nom'])->get();

        return response()->json(["data"=>$data,200]);
    }
}
