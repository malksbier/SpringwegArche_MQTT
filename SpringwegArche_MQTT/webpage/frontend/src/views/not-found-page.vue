<template>
    <div>
        <h1>{{$t("page.not_found")}}</h1>
        <p>{{$t("message.not_found")}} 
            <router-link to="/"> {{$t("page.home")}} </router-link>
        </p>
    </div>
</template>