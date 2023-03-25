@component('mail::message')
<p style="color:#000;font-weight:600">
    Hi {{$user->LastName}}<br>
    We're happy you signed up for MyEduConnect.To start<br>
    exploring the App.<br>
    please confirm your emails address.
</p>
 @component('mail::panel')
 <p style="text-align:center">  Lien de Verification </p>
      <br>
    <x-mail::button url="{{env('APP_FRONTEND')}}/pageHome?email={{$user->email}}" color="success">
        Verify Now
    </x-mail::button>
 @endcomponent
 <p style="text-align:center"> SpeedyTurtles Team</p>
@endcomponent

