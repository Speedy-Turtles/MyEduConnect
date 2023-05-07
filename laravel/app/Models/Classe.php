<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Classe extends Model
{
    use HasFactory;
    protected $guarded=[];
    public function users(){
        return $this->hasMany(User::class);
    }

    public function emploi(){
        return $this->hasOne(Emploi::class);
    }


    public function Specialite(){
        return $this->belongsTo(Specialite::class);
    }
}
