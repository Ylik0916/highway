import request from '@/utils/request'

// 查询项目信息管理列表
export function listProjectInformation(query) {
  return request({
    url: '/system/projectInformation/allInformationList',
    method: 'get',
    params: query
  })
}

// 查询项目信息管理详细
export function getProjectInformation(projectId) {
  return request({
    url: '/system/projectInformation/' + projectId,
    method: 'get'
  })
}

// 新增项目信息管理
export function addProjectInformation(data) {
  return request({
    url: '/system/projectInformation',
    method: 'post',
    data: data
  })
}

// 修改项目信息管理
export function updateProjectInformation(data) {
  return request({
    url: '/system/projectInformation',
    method: 'put',
    data: data
  })
}

// 删除项目信息管理
export function delProjectInformation(projectId) {
  return request({
    url: '/system/projectInformation/' + projectId,
    method: 'delete'
  })
}

// 查询标段信息管理详细
export function getBdInformation(projectId) {
  return request({
    url: '/system/supervisor/bd/' + projectId,
    method: 'get'
  })
}

// 删除项目信息管理==》监理标段
export function delSupervisor(supervisorId) {
  return request({
    url: '/system/supervisor/' + supervisorId,
    method: 'delete'
  })
}

// 新增项目信息管理==》监理标段
export function addSupervisor(data) {
  return request({
    url: '/system/supervisor',
    method: 'post',
    data: data
  })
}

// 查询项目信息管理==》监理标段详细
export function getSupervisor(supervisorId) {
  return request({
    url: '/system/supervisor/' + supervisorId,
    method: 'get'
  })
}

// 修改项目信息管理==》监理标段
export function updateSupervisor(data) {
  return request({
    url: '/system/supervisor',
    method: 'put',
    data: data
  })
}

// 查询项目信息管理==》施工标段详细
export function getConstruction(constructionProjectId) {
  return request({
    url: '/system/construction/constructionList/' + constructionProjectId,
    method: 'get'
  })
}

// 删除项目信息管理==》施工标段详细
export function delConstruction(constructionId) {
  return request({
    url: '/system/construction/' + constructionId,
    method: 'delete'
  })
}
