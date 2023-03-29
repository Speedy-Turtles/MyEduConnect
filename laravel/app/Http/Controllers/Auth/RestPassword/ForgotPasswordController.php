<?php

namespace App\Http\Controllers\Auth\RestPassword;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Mail;
use App\Mail\ResetPassword;
use App\Mail\SendTokenPassword;

class ForgotPasswordController extends Controller
{
   public function ForgotPassword(String $email){
        $user=User::where('email',$email)->firstOrFail();
        $token=mt_rand(10000,99999);
        $user->password_token=$token;
        $user->password_token_send_at=now();
        $user->save();
        Mail::to($email)->send(new SendTokenPassword($token));
        return response()->json(["data"=>"Code Send To Your Email successfully"],200);
   }

   public function ChangerPassword(Request $request){
    $user=User::where("email",$request->email)->where("password_token",$request->token)->first();
    if($user){
       if($user->password_token_send_at > now()->addHour()){
         return response()->json(["data"=>"Token Expired"],404);
        }
        $user->update([
            "password"=>bcrypt($request->password),
            "password_token_send_at"=>null,
            "password_token"=>null
        ]);
        return response()->json(["data"=>"Password Changed With success"],200);
    }else{
        return response()->json(["data"=>"Email Or Token not found"],404);
    }
   }
}
