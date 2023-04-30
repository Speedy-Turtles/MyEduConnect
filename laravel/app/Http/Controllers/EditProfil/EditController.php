<?php

namespace App\Http\Controllers\EditProfil;

use App\Http\Controllers\Controller;
use App\Http\Requests\EditStore;
use App\Models\User;
use Illuminate\Http\Request;

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
}
