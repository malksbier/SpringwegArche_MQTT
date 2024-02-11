/*
Swap Page without error cause Router is on the page

IMPL
import {switchPageSafely} from "@/util/RouterUtil"
methods: {
    switchPageSafe(path) {
      switchPageSafely(this.$route, this.$router,path)
    },
  }w
*/
export function switchPageSafely(route, router, path) {
    //console.log("path: " + path);
    //console.log("where we are now: " + this.$route.fullPath);
    
    if(String(route.fullPath) != path) {
        router.push(path);
      //console.log("change path to: " + path);
    } else {
      //console.log("not change path to: " + path);
    }
}
    