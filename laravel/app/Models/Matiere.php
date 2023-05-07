<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Matiere extends Model
{
    public function seance(){
        return $this->belongsTo(Seance::class);
    }
    use HasFactory;
}
