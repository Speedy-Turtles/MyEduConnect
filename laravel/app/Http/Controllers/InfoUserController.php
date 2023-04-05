<?php

namespace App\Http\Controllers;

use App\Models\Classe;
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

    public function testExistToken(String $token){
        $user=User::where("password_token",$token)->first();
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

    public function GetNiveauUser(Request $request){
        $user=User::where("id",$request->user()->id)->first();
        $classe=Classe::where("id",$user->classe_id)->with("Specialite")->first();
        return response()->json([
            "data"=>$classe,
        ],200);
        /*if($user){
            return response()->json([
                "data"=>"Exist",
                "success"=>false
            ],201);
        }else{
            return response()->json([
                "data"=>"not Exist",
                "success"=>true
            ]);
        }*/
    }

    public function ActiveUser($id,Request $request){
        $user=RoleUser::where('user_id',$id)->update(['status'=>$request->status]);
        return response()->json([
            "data"=>$user,
        ],200);
    }

}
