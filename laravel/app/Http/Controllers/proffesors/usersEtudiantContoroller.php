<?php

namespace App\Http\Controllers\proffesors;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;

class usersEtudiantContoroller extends Controller
{
    public function acceptedStudents(){

    }
    public function pendingRequests(){
        $pendingRequests = User::whereHas('roles', function($query){
            $query->where('Role_name', 'etudiant')->where('status',0);
        })->with('classe')->get();
        if(empty($pendingRequests)){
            return response()->json(["data"=>"no data found!"],404);
        }
        return response()->json(["data"=>$pendingRequests],200);
    }
}
