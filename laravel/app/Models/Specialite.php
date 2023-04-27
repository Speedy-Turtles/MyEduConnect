<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Specialite extends Model
{
    use HasFactory;
    protected $guarded=[];
    public function classes(){
        return $this->hasMany(Classe::class);
    }
}
