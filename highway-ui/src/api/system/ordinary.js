import request from '@/utils/request'

// 查询一般养护列表
export function listOrdinary(query) {
  return request({
    url: '/system/ordinary/list',
    method: 'get',
    params: query
  })
}

// 查询一般养护详细
export function getOrdinary(mpoid) {
  return request({
    url: '/system/ordinary/' + mpoid,
    method: 'get'
  })
}

// 新增一般养护
export function addOrdinary(data) {
  return request({
    url: '/system/ordinary',
    method: 'post',
    data: data
  })
}

// 修改一般养护
export function updateOrdinary(data) {
  return request({
    url: '/system/ordinary',
    method: 'put',
    data: data
  })
}

// 删除一般养护
export function delOrdinary(mpoid) {
  return request({
    url: '/system/ordinary/' + mpoid,
    method: 'delete'
  })
}
