<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Salle extends Model
{
    public function emplois(){
        return $this->belongsToMany(Emploi::class);
    }
    use HasFactory;
}
