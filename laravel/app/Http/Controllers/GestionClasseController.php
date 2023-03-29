<?php

namespace App\Http\Controllers;

use App\Models\Classe;
use Illuminate\Http\Request;

class GestionClasseController extends Controller
{
    public function getClasseForSpecialte($id){
      $classes=Classe::where("specialite_id",$id)->get();
      return response()->json(["data"=>$classes],200);
    }
}
