export default function getRandomString(): string {
    var N = 8
    return btoa(String.fromCharCode(...crypto.getRandomValues(new Uint8Array(N)))).substring(0, N)

}