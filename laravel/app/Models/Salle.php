<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Salle extends Model
{
    public function users(){
        return $this->belongsToMany(Role::class,"emploi")->withPivot(['id']);
    }
    use HasFactory;
}
