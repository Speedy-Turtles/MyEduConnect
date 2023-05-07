<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Seance extends Model
{
    use HasFactory;

    public function emplois(){
        return $this->hasMany(Emploi::class);
    }
    public function salles(){
        return $this->hasMany(Salle::class);
    }

    public function users(){
        return $this->hasMany(Seance::class);
    }

    public function matieres(){
        return $this->hasMany(Matiere::class);
    }
}
