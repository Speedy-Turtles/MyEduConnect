<?php

namespace App\Http\Controllers;

use App\Models\nouveaute;
use Illuminate\Http\Request;

class nouveauteController extends Controller
{
    public function getAllNouveaute(Request $request)
    {
        $search = $request->search;
        if (isset($search)) {
            $nouveautes = nouveaute::where("titre", "Like", "%" . $search . "%")->get();
        } else {
            $nouveautes = nouveaute::all();
        }
        if (!empty($nouveautes)) {
            return response()->json(["data" => $nouveautes], 200);
        }
        return response()->json(["data" => []], 404);
    }
    public function addnouveaute(Request $request)
    {
        /*$imagePath = public_path($request->photo);
        $imageData = file_get_contents($imagePath);
        $photo = base64_encode($imageData);*/
        $nouveaute = nouveaute::create([
            "titre" => $request->titre,
            "descripition" => $request->descripition,
            "photo" => $request->photo,
        ]);
        $nouveaute->save();
        if ($nouveaute == null) {
            return response()->json(["data" => null], 400);
        } else {
            return response()->json(["data" => $nouveaute], 201);
        }
    }
    public function deletenouveaute($id)
    {
        $nouveaute = nouveaute::find($id);
        if ($nouveaute != null) {
            $nouveaute->delete();
            return response()->json(["data" => "neveautly deleted succesfully"], 200);
        }
        return response()->json(["data" => "neveautly not found"], 404);
    }
    public function getnouveauteById($id)
    {
        $nouveaute = nouveaute::find($id);
        if ($nouveaute != null) {
            return response()->json(["data" => $nouveaute], 200);
        }
        return response()->json(["data" => "neveautly not found"], 404);
    }
    public function updateNouveaute($id, Request $request)
    {
        $nouveaute = nouveaute::find($id);
        if ($nouveaute != null) {
            $nouveaute->update([
                'titre' => $request->titre,
                'descripition' => $request->descripition,
                'photo' => $request->photo
            ]);
            return response()->json(["data" => "neveautly updated succesfully"], 200);
        }
        return response()->json(["data" => "neveautly not found"], 404);
    }
}
