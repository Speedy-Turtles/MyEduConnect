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
}
