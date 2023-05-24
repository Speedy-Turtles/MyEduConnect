<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;

use App\Notifications\EditProfil\SendChangerEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

class User extends Authenticatable
{
    use HasApiTokens, HasFactory, Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
   /* protected $fillable = [
        'name',
        'email',
        'password',
    ];*/
    protected $guarded=[];

    /**
     * The attributes that should be hidden for serialization.
     *
     * @var array<int, string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    /**
     * The attributes that should be cast.
     *
     * @var array<string, string>
     */
    protected $casts = [
        'email_verified_at' => 'datetime',
    ];

    public function SendEmailChanger($email){
        $this->notify(new SendChangerEmail($email));
    }

    public function roles(){
        return $this->belongsToMany(Role::class,"role_users")->withPivot(['status']);
    }

    public function notifications(){
        return $this->hasMany(Notification::class);
    }

    public function classe(){
        return $this->belongsTo(Classe::class);
    }

    public function demandes(){
        return $this->hasMany(Demande::class);
    }

    public function VoteSessions(){
        return  $this->belongsToMany(VoteSession::class,'user_nominateds');
    }

    public function votes(){
        return  $this->hasMany(Votes::class,'votes');
    }

    public function seance(){
        return $this->hasMany(Seance::class,'seances');
    }

    public function forums(){
        return $this->hasMany(Forum::class);
    }

    public function inboxes(){
        return $this->hasMany(Inboxe::class);
    }

    public function Suggestions(){
        return $this->belongsToMany(Suggestion::class);
    }

}
