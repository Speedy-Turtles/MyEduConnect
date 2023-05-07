<?php

namespace App\Http\Controllers\EditProfil;

use App\Http\Controllers\Controller;
use App\Http\Requests\EditStore;
use App\Mail\VerifyMail;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Mail;

class EditController extends Controller
{
    public function editPhoto(Request $request){
        $file_name = time() . '_' .$request->photo->getClientOriginalName();
        $image=$request->file('photo')->storeAs('users',$file_name,'public');
        $image_name='/storage/'.$image;
        $user=User::find($request->user()->id);
        if($user){
            $user->update([
                "Photo"=> $image_name
            ]);
            return response()->json(['data'=>$user,"message"=>"Photo Changed with Success"],200);
        }else{
            return response()->json(['data'=>"User Not Found"],404);
        }
    }

    public function EditInfoPersonnel(Request $request){
        $user=User::find($request->user()->id);
        if($user){
            $user->update([
                "LastName"=>$request->lastname,
                "FirstName"=>$request->firstname,
                "Cin"=>$request->cin,
                "num_tlf"=>$request->num_tlf,
                "Birth_day"=>$request->birth_day,
            ]);
            return response()->json(['data'=>$user,"message"=>"User Changed with Success"],200);
        }else{
            return response()->json(['data'=>"User Not Found"],404);
        }
    }

    public function CheckPassword(String $password,Request $request){
        $user=User::find($request->user()->id);
        $check=Hash::check($password, $user->password);
        if($check){
            return response()->json([
                "data"=>"equal",
                "success"=>true
            ],201);
        }else{
            return response()->json([
                "data"=>"not equal",
                "success"=>false
            ]);
        }
    }

    public function ChangerPasswordActuel(String $password,Request $request){
        $user=User::find($request->user()->id);
        if($user){
             $user->update([
                "password"=>bcrypt($password)
             ]);
             return response()->json(['data'=>$user,"message"=>"User Changed with Success"],200);
         }else{
        return response()->json(['data'=>"User Not Found"],404);
        }
    }

    public function SendChangedEmail(String $email,Request $request){
        $user=User::find($request->user()->id);
        $user->SendEmailChanger($email);
    }

    public function updateEmail(request $request){
        $user = User::where('email', $request->email_old)->first();
        if($user){
            $user->update([
                'email'=>$request->email_new,
                'email_verified_at'=>Null
            ]);
            Mail::to($request->email_new)->send(new VerifyMail($user));
            return response()->json(["data" =>$user,"message"=>"Email Has Been Changed With Success"], 200);
        }else{
            return response()->json(["data" => "User Not Found"], 404);
        }
    }

}
