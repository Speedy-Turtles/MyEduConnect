<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

class LoginController extends Controller
{
    public function Login(Request $request){
        if(Auth::attempt(['email'=>$request->email,'password'=>$request->password])){

            $authUser=Auth::user();
            if (!$authUser->hasVerifiedEmail()) {
                return response()->json(['data' => "Your email is not verified", 'status' => "email"], 404);
            }

            $user=User::where('id',$authUser->id)->with('roles')->first();
            $verify["Isetudiant"]=false;
            $verify["Isenseignant"]=false;
            $verify["IsChefDepartement"]=false;
            $verify["Istechnicien"]=false;
            $verify["Isadmin"]=false;

            if($user->roles->contains("Role_name","chefDepartment")){
                if($user->roles->first()->pivot->status==0){
                    return response()->json(['data' => "Your account not actived"], 404);
                }else if($user->roles->first()->pivot->status==2){
                    return response()->json(['data' => "Your account Rejected"], 404);
                }else{
                    $verify["IsChefDepartement"]=true;
                }
            }

            if($user->roles->contains("Role_name","etudiant")){
                if($user->roles->first()->pivot->status==0){
                    return response()->json(['data' => "Your account not actived"], 404);
                }else if($user->roles->first()->pivot->status==2){
                    return response()->json(['data' => "Your account Rejected"], 404);
                }else{
                    $verify["Isetudiant"]=true;
                }
            }

            if($user->roles->contains("Role_name","ensignant")){
                if($user->roles->first()->pivot->status==0){
                    return response()->json(['data' => "Your account not actived"], 404);
                }else if($user->roles->first()->pivot->status==2){
                    return response()->json(['data' => "Your account Rejected"], 404);
                }else{
                    $verify["Isenseignant"]=true;
                }
            }

            if($user->roles->contains("Role_name","Technicien")){
                if($user->roles->first()->pivot->status==0){
                    return response()->json(['data' => "Your account not actived"], 404);
                }else if($user->roles->first()->pivot->status==2){
                    return response()->json(['data' => "Your account Rejected"], 404);
                }else{
                    $verify["Istechnicien"]=true;
                }
            }

            $verify["token"]=$user->createToken("api_token")->plainTextToken;
            $verify["user"]=$user;
            return response()->json(["data"=>$verify],200);

        }else{

            return response()->json(["data"=>"User Not Found"],404);

        }
    }
}
