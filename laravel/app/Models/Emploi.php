<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Emploi extends Model
{
    use HasFactory;
    protected $guarded=[];
    public function salle(){
        return $this->belongsTo(Salle::class);
    }
    public function user(){
        return $this->belongsTo(User::class);
    }
}
