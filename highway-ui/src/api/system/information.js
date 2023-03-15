import request from '@/utils/request'

// 查询路线列表列表
export function listInformation(query) {
  return request({
    url: '/system/information/allInformationList',
    method: 'get',
    params: query
  })
}

// 查询路线列表详细
export function getInformation(id) {
  return request({
    url: '/system/information/' + id,
    method: 'get'
  })
}

// 新增路线列表
export function addInformation(data) {
  return request({
    url: '/system/information',
    method: 'post',
    data: data
  })
}

// 修改路线列表
export function updateInformation(data) {
  return request({
    url: '/system/information',
    method: 'put',
    data: data
  })
}

// 删除路线列表
export function delInformation(id) {
  return request({
    url: '/system/information/' + id,
    method: 'delete'
  })
}
