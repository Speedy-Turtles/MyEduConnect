<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Http\Requests\StoreSuggestion;
use App\Models\Suggestion;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Http;

class SuggestionController extends Controller
{
    public function __construct()
    {
        $this->authorizeResource(Suggestion::class,'Suggestion');
    }
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
            $all_sugg=Suggestion::paginate(3);
            return response()->json(['data'=>$all_sugg],200);
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreSuggestion $request)
    {
         $sugg=new Suggestion();
         $sugg->user_id=$request->user()->id;
         $sugg->type=$request->type;
         $sugg->Message=$request->message;
         $sugg->save();
         return response()->json(['data'=>$sugg],201);
    }

    /**
     * Display the specified resource.
     */
    public function show(Suggestion $suggestion)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Suggestion $suggestion)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Suggestion $suggestion)
    {
       $sugg=Suggestion::find($suggestion->id);
       if($sugg){
            $sugg->delete();
            return response()->json(['data'=>"delete with success"],200);
       }else{
        return response()->json(['data'=>"Not Found"],404);
       }
    }
}
