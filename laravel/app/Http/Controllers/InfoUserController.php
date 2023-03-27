<?php

namespace App\Http\Controllers;

use App\Models\Role;
use App\Models\RoleUser;
use App\Models\User;
use Illuminate\Http\Request;

class InfoUserController extends Controller
{
    public function testExistEmail(String $email){
        $user=User::where("email",$email)->first();
        if($user){
            return response()->json([
                "data"=>"Exist",
                "success"=>false
            ],201);
        }else{
            return response()->json([
                "data"=>"not Exist",
                "success"=>true
            ]);
        }
    }


    public function ActiveUser($id){
        $user=RoleUser::where('user_id',$id)->update(['status'=>1]);
        return response()->json([
            "data"=>$user,
        ],200);
    }

}
