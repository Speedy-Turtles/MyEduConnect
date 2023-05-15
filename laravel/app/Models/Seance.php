<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Seance extends Model
{
    use HasFactory;
    protected $guarded=[];
    public function emploi(){
        return $this->belongsTo(Emploi::class);
    }

    public function salle(){
        return $this->belongsTo(Salle::class);
    }

    public function user(){
        return $this->belongsTo(User::class);
    }

    public function matiere(){
        return $this->belongsTo(Matiere::class);
    }
}
