import request from '@/utils/request'

// 查询精品示范列表
export function listDelicacy(query) {
  return request({
    url: '/system/delicacy/list',
    method: 'get',
    params: query
  })
}

// 查询精品示范详细
export function getDelicacy(mpdid) {
  return request({
    url: '/system/delicacy/' + mpdid,
    method: 'get'
  })
}

// 新增精品示范
export function addDelicacy(data) {
  return request({
    url: '/system/delicacy',
    method: 'post',
    data: data
  })
}

// 修改精品示范
export function updateDelicacy(data) {
  return request({
    url: '/system/delicacy',
    method: 'put',
    data: data
  })
}

// 删除精品示范
export function delDelicacy(mpdid) {
  return request({
    url: '/system/delicacy/' + mpdid,
    method: 'delete'
  })
}
