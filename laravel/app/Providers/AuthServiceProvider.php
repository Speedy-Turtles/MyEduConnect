<?php

namespace App\Providers;

// use Illuminate\Support\Facades\Gate;

use App\Models\Suggestion;
use App\Models\User;
use App\Policies\SuggestionPolicy;
use Illuminate\Auth\Access\Response;
use Illuminate\Foundation\Support\Providers\AuthServiceProvider as ServiceProvider;
use Illuminate\Support\Facades\Gate;

class AuthServiceProvider extends ServiceProvider
{
    /**
     * The model to policy mappings for the application.
     *
     * @var array<class-string, class-string>
     */
    protected $policies = [
        // 'App\Models\Model' => 'App\Policies\ModelPolicy',
        Suggestion::class=>SuggestionPolicy::class,
    ];

    /**
     * Register any authentication / authorization services.
     */
    public function boot(): void
    {

       /* Gate::define('admin',function(User $user){
            return $user->roles->contains("Role_name","Admin");
        });*/
    }
}
