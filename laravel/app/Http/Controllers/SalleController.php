<?php

namespace App\Http\Controllers;

use App\Models\Salle;
use Illuminate\Http\Request;

class SalleController extends Controller
{
    public function getAllSalles(){
        $salles=Salle::all();
        if($salles){
            return response()->json(["data"=>$salles,200]);
        }
        return response()->json(["data"=>[],404]);
    }
}
