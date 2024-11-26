import axios from "@/axios"

export function login(phone, password) {
    return axios.post("/sso/admin/login", {phone, password})
}

export function getAdminInfo() {
    return axios.post("/admin/detail")
}

export function updateAdminPassword(data) {
    return axios.post("/admin/password/update", data)
}