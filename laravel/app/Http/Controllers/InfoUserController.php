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
    }

    public function ActiveUser($id){
        $user=RoleUser::where('user_id',$id)->update(['status'=>1]);
        return response()->json([
            "data"=>$user,
        ],200);
    }

    public function show_etudiant(){

         // without fields roles
        $user=User::whereHas('roles',function($query){
            $query->where('Role_name','etudiant')->where('status',1);
        })->get();

         // with fields roles
        $users = User::whereHas('roles', function ($query) {
            $query->where('Role_name', 'etudiant')->where('status',1);
        })->with('roles')->get();

        return response()->json(['data'=>$users],200);
    }

}
