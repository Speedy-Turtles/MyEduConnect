<?php

namespace App\Http\Controllers\proffesors;

use App\Http\Controllers\Controller;
use App\Models\Classe;
use App\Models\Role;
use App\Models\RoleUser;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class UsersProffesorsController extends Controller
{

    public  function acceptedProffesors()
    {
        $enseignants = User::whereHas('roles', function($query){
            $query->where('Role_name', 'ensignant')->where('status',1);
        })->get();
        if(empty($enseignants)){
            return response()->json(["data"=>"no data found!"],404);
    }
    return response()->json(["data"=>$enseignants],200);

}
    public function pendingRequests(){
        $pendingRequests = User::whereHas('roles', function($query){
            $query->where('Role_name', 'ensignant')->where('status',0);
        })->get();
        if(empty($pendingRequests)){
            return response()->json(["data"=>"no data found!"],404);
        }
        return response()->json(["data"=>$pendingRequests],200);
    }
}
