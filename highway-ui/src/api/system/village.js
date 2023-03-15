import request from '@/utils/request'

// 查询建制村列表
export function listVillage(query) {
  return request({
    url: '/system/village/list',
    method: 'get',
    params: query
  })
}

// 查询建制村详细
export function getVillage(villageId) {
  return request({
    url: '/system/village/' + villageId,
    method: 'get'
  })
}

// 新增建制村
export function addVillage(data) {
  return request({
    url: '/system/village',
    method: 'post',
    data: data
  })
}

// 修改建制村
export function updateVillage(data) {
  return request({
    url: '/system/village',
    method: 'put',
    data: data
  })
}

// 删除建制村
export function delVillage(villageId) {
  return request({
    url: '/system/village/' + villageId,
    method: 'delete'
  })
}
