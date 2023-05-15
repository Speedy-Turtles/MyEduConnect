<?php

namespace App\Http\Controllers\Forum;

use App\Http\Controllers\Controller;
use App\Models\Forum;
use App\Models\Inboxe;
use App\Models\User;
use Illuminate\Http\Request;

class ForumController extends Controller
{
    // public function getForums(){
    //     $data=Forum::with('user')->get();
    //     return response()->json(['data'=>$data],200);
    // }

    public function getForums(){
        $data = Forum::leftJoin('users', 'forums.user_id', '=', 'users.id')
                    ->select('forums.*', 'users.*')
                    ->get();
        return response()->json(['data'=>$data],200);
    }

    public function addPost(Request $request){
                $post=new Forum();
                $post->user_id=$request->user()->id;
                $post->post =$request->post;
                $post->save();
                return response()->json(['data'=>$post,201]);
    }

    public function getAllUsers(){
        $users=User::with('roles')->get();
        return response()->json(['data'=>$users,200]);
    }

    public function getChatById(int $id,Request $request){
       $data=Inboxe::where('id_envoi', $request->user()->id)
                    ->where('id_recu', $id)
                    ->orWhere('id_envoi', $id)
                    ->where('id_recu', $request->user()->id)
                    ->orderBy('created_at')
                    ->get();
        return response()->json(['data'=>$data,200]);

    }

    public function getUserById(int $id,Request $request){
        $user=User::where('id',$id)->get();
        return response()->json(['data'=>$user,200]);
    }

    public function addMessage(int $id,Request $request){
        $message=new Inboxe();
        $message->id_envoi=$request->user()->id;
        $message->id_recu=$id;
        $message->message=$request->message;
        $message->save();
        return response()->json(['data'=>$message,201]);

}
}
