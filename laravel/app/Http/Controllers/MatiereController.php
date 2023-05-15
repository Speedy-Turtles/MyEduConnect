<?php

namespace App\Http\Controllers;

use App\Models\Matiere;
use Illuminate\Http\Request;

class MatiereController extends Controller
{
    public function getAllMatiere(){
        $matieres=Matiere::all();
        if($matieres){
            return response()->json(["data"=>$matieres,200]);
        }
        return response()->json(["data"=>[],404]);
    }
}
