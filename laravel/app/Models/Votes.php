<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Votes extends Model
{
    use HasFactory;

    public function users(){
        return  $this->belongsToMany(User::class);
    }

    public function votesSessions(){
        return $this->belongsTo(VoteSession::class);
    }

    public function user_nominated(){
        return $this->belongsTo(User::class,"user_nominated_id");
    }

}
