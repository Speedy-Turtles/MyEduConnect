<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use App\Mail\VerifyMail;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Mail;
use Nette\Utils\Strings;

class VerifyEmailController extends Controller
{
    public function VerifyEmail(String $email){
        $user=User::where('email',$email)->first();
        if(!$user->hasVerifiedEmail()){
            $user->markEmailAsVerified();
            return response()->json(['data'=>"Email has been verified"],200);
        }else{
            return response()->json(['data'=>"email already verified"],201);
        }
    }

    public function renvoyer(String $email){
        $user=User::where('email',$email)->first();
        if($user->hasVerifiedEmail()){
            return response()->json(['data'=>"email already verified"],200);
        }
        Mail::to($user->email)->send(new VerifyMail($user));
        return response()->json(['data'=>"Link verified send with success"],200);
    }

}
