<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use App\Http\Requests\StoreRequest;
use App\Mail\VerifyMail;
use App\Models\Role;
use App\Models\RoleUser;
use App\Models\User;
use App\Notifications\VerifyEmail;
use Dotenv\Validator;
use Illuminate\Http\Request;
use Illuminate\Notifications\Notification;
use Illuminate\Support\Facades\Mail;

class RegisterController extends Controller
{
    public function Signup(StoreRequest $request)
    {

       if ($request->hasFile('photo')) {
            $file_name = time() . '_' .$request->photo->getClientOriginalName();
            $image=$request->file('photo')->storeAs('users',$file_name,'public');
            $image_name='/storage'.$image;
        }else{
            $image_name=$request->firstname[0].''.$request->lastname[0];
        }

        $user=User::create([
            "LastName"=>$request->lastname,
            "FirstName"=>$request->firstname,
            "email"=>$request->email,
            "password"=>bcrypt($request->password),
            "Cin"=>$request->cin,
            "sex"=>$request->sex,
            "photo"=>$image_name,
            "num_tlf"=>$request->num_tlf,
            "Birth_day"=>$request->birth_day,
            "classe_id"=>$request->classe_id ? $request->classe_id : null
        ]);

        $role=new Role();
        $role->Role_name=$request->Role_name;
        $role->save();

        $role_user=new RoleUser();
        $role_user->user_id=$user->id;
        $role_user->role_id=$role->id;
        $role_user->save();

        Mail::to($request->email)->send(new VerifyMail($user));
        //$user->markEmailAsVerified();
        return response()->json(['data'=>"user created"],200);
    }


}
