import request from '@/utils/request'

// 查询标识标牌列表
export function listSignage(query) {
  return request({
    url: '/system/signage/list',
    method: 'get',
    params: query
  })
}

// 查询标识标牌详细
export function getSignage(identificationTagId) {
  return request({
    url: '/system/signage/' + identificationTagId,
    method: 'get'
  })
}

// 新增标识标牌
export function addSignage(data) {
  return request({
    url: '/system/signage',
    method: 'post',
    data: data
  })
}

// 修改标识标牌
export function updateSignage(data) {
  return request({
    url: '/system/signage',
    method: 'put',
    data: data
  })
}

// 删除标识标牌
export function delSignage(identificationTagId) {
  return request({
    url: '/system/signage/' + identificationTagId,
    method: 'delete'
  })
}
