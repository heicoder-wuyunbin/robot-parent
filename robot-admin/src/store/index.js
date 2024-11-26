import { createStore } from 'vuex'
import { getAdminInfo } from '@/api/admin/user'
import { removeToken } from '@/composables/auth'
import { useRouter } from 'vue-router'

const router = useRouter()

// 创建一个新的 store 实例
const store = createStore({
    state() {
        return {
            // 用户信息
            user: {},
            setting: {},
            menuWidth: "250px"
        }
    },
    mutations: {
        // 设置全局用户信息
        SET_USERINFO(state, user) {
            state.user = user
        },
        // 设置博客设置信息
        SET_BLOG_SETTING(state, setting) {
            state.setting = setting
        },
        // 展开或缩起侧边栏
        HANDLE_MENU_WIDTH(state) {
            state.menuWidth = state.menuWidth == "250px" ? "64px" : "250px"
        }

    },
    actions: {
        // 获取用户登录信息
        // 入参 commit 相当于 store.commit


        logout({ commit }) {
            removeToken()
            // 删除当前全局的 user 状态
            commit('SET_USERINFO', {})
        }
    }
})

// 暴露
export default store