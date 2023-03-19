import request from '@/utils/request'

// 查询现代产业园列表
export function listIndustrial(query) {
  return request({
    url: '/system/industrial/list',
    method: 'get',
    params: query
  })
}

// 查询现代产业园详细
export function getIndustrial(industrialParkId) {
  return request({
    url: '/system/industrial/' + industrialParkId,
    method: 'get'
  })
}

// 新增现代产业园
export function addIndustrial(data) {
  return request({
    url: '/system/industrial',
    method: 'post',
    data: data
  })
}

// 修改现代产业园
export function updateIndustrial(data) {
  return request({
    url: '/system/industrial',
    method: 'put',
    data: data
  })
}

// 删除现代产业园
export function delIndustrial(industrialParkId) {
  return request({
    url: '/system/industrial/' + industrialParkId,
    method: 'delete'
  })
}
