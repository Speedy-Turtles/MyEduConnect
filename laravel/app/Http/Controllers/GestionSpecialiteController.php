<?php

namespace App\Http\Controllers;

use App\Models\Specialite;
use Illuminate\Http\Request;

class GestionSpecialiteController extends Controller
{
    public function getSpecialte()
    {
        $sepecialtes = Specialite::with('classes')->get();
        return response()->json(["data" => $sepecialtes], 200);
    }
    public function addSpecialite(Request $request)
    {
        $specialite = Specialite::create([
            "type" => $request->type,
            "niveau" => $request->niveau,
        ]);
        $specialite->save();
        if ($specialite == null) {
            return response()->json(["data" => null], 400);
        } else {
            return response()->json(["data" => $specialite], 201);
        }
    }
    public function DeleteSpecialite($id){
        $specialite=Specialite::find($id);
        if($specialite){
        Specialite::where('id',$id)->with('classes')->delete();
        Specialite::where('id',$id)->delete();
        return response()->json(["data"=>"specialite deleted succesfully"],200);
        }
        return response()->json(["data"=>"specialite not found"],404);
    }
}
