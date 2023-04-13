<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class user_nominated extends Model
{
    use HasFactory;
    protected $guarded=[];

    public function votes(){
        return $this->hasMany(Votes::class);
    }

}
