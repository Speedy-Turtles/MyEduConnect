<?php

namespace App\Http\Controllers;

use App\Models\Specialite;
use Illuminate\Http\Request;

class GestionSpecialiteController extends Controller
{
    public function getSpecialte(Request $request)
    {
        $search=$request->search;
        if(isset($search)){
        $sepecialtes = Specialite::where("type","Like","%". $search ."%")->with('classes')->paginate(2);
        if($sepecialtes){
        return response()->json(["data" => $sepecialtes], 200);
        }else{
            return response()->json(["data" => []], 404);
        }
    }else{
        $sepecialtes = Specialite::with('classes')->paginate(2);
        return response()->json(["data" => $sepecialtes], 200);
    }
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
    public function getElementById($id){
        $specialite=Specialite::find($id);
        if($specialite){
            return response()->json(["data"=>$specialite],200);
        }
        return response()->json(["data"=>"specialite not found"],404);
    }
    public function updateSpecialite(Request $request,$id){
        $specialite=Specialite::find($id);
        if($specialite){
            Specialite::whereId($id)->update([
                'type'=>$request->type,
                'niveau'=>$request->niveau
        ]);
            return response()->json(["data"=>"data updated succesfully"],200);
        }
        return response()->json(["data"=>"specialite not found"],404);
    }
}
