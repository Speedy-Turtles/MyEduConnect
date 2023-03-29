<?php

namespace App\Http\Controllers;

use App\Models\Specialite;
use Illuminate\Http\Request;

class GestionSpecialiteController extends Controller
{
    public function getSpecialte(){
        $sepecialtes=Specialite::All();
        return response()->json(["data"=>$sepecialtes],200);
      }
}
